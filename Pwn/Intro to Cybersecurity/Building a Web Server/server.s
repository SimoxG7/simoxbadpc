.intel_syntax noprefix
.globl _start

.section .data
	BUFFER: .space 256 #buffer
	RESPONSE_STRING: .string "HTTP/1.0 200 OK\r\n\r\n" #response 
	FILE: .space 16
	READ_BUFFER: .space 256 

.section .text

_start:
	#socket(AF_INET; SOCK_STREAM, IPPROTO_IP)
	mov rax, 41 #socket syscall (41)
	mov rdi, 2 #AF_INET value (2)
	mov rsi, 1 #SOCK_STREAM value (1)
	xor rdx, rdx #IPPROTO_IP value (0)
	syscall

	mov rdi, rax #socketfd = 3
	mov r15, rax #copy of socketfd

	#bind
	#preparesockaddr struct
	push dword ptr 0x00000000 #4 bytes padding (needed because it's a 16 byte address size)
	push dword ptr 0x00000000 #4 bytes padding
	push dword ptr 0x00000000 #INET_ADDRESS: 0.0.0.0 (any)
	push word ptr 0x5000 #port 80 (little endian?)
	push word ptr 0x0002 #AF_INET value (2)
	mov rsi, rsp #starting point of struct address
	mov rdx, 16 #address len
	mov rax, 49 #bind syscall (49)
	syscall

	#listen
	#rdi already with socket fd
	xor rsi, rsi # backlog 0
	mov rax, 50 #listen syscall (50)
	syscall

BACK_TO_ACCEPT:
	#accept
	#rdi already with socket fd
	mov rsi, 0 #null sockaddr 
	mov rdx, 0 #address len (null)
	mov rax, 43 #accept syscall (43)
	syscall

	mov r14, rax #accept fd

	#fork
	mov rax, 57 #fork syscall (57)
	syscall

	cmp rax, 0 #child
	jne PARENT

CHILD:
	#close
	#close file
	mov rdi, r15 #rdi already open file fd
	mov rax, 3 #close syscall (3)
	syscall

	#read
	mov rdi, r14 #accept fd
	lea rsi, [BUFFER] #address of buffer
	mov rdx, 256 #size of how much to read
	xor rax, rax #read syscall (0)
	syscall

	#open
	#open file
	xor r13, r13
	add r13, [BUFFER + 4]
	mov [FILE], r13
	xor r13, r13
	add r13, [BUFFER + 12]
	mov [FILE + 8], r13
	lea rdi, [FILE] #file in /GET request
	mov rsi, 0 #null flags
	mov rdx, 0 #O_RDONLY
	mov rax, 2 #open syscall (2)
	syscall

	mov r13, rax #open file fd

	#read
	#read file
	mov rdi, r13 #open file fd
	lea rsi, [READ_BUFFER] #address of buffer
	mov rdx, 256 #size of how much to read
	xor rax, rax #read syscall (0)
	syscall

	mov r12, rax; #lenght of the read string from file

	#close
	#close file
	#rdi already open file fd
	mov rax, 3 #close syscall (3)
	syscall

	#write ok
	mov rdi, r14 #accept fd
	lea rsi, [RESPONSE_STRING] #response string in buffer
	mov rdx, 19 #size of response string
	mov rax, 1 #write syscall (1)
	syscall

	#write
	#write file
	mov rdi, r14 #accept fd
	lea rsi, [READ_BUFFER] #read string in buffer
	mov rdx, r12 #size of response string
	mov rax, 1 #write syscall (1)
	syscall

	##close
	#mov rdi, r14 #accept fd
	#mov rax, 3 #close syscall (3)
	#syscall

	##accept
	#mov rdi, r15 #socket fd
	#mov rsi, 0 #null sockaddr 
	#mov rdx, 0 #address len (null)
	#mov rax, 43 #accept syscall (43)
	#syscall

	#exit
	mov rax, 60 #exit syscall (60)
	xor rdi, rdi #error_code (0)
	syscall

PARENT:

	#close
	mov rdi, r14 #accept fd
	mov rax, 3 #close syscall (3)
	syscall

	mov rdi, r15 #socket fd
	mov rsi, 0 #null sockaddr 
	mov rdx, 0 #address len (null)
	mov rax, 43 #accept syscall (43)
	syscall

	jmp BACK_TO_ACCEPT

	#exit
	mov rax, 60 #exit syscall (60)
	xor rdi, rdi #error_code (0)
	syscall
