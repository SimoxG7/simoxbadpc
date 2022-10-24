.intel_syntax noprefix 
.global _start 

.section .data
    str: .long 100  # alloco buffer di 100 byte

.section .text

_start:

# read
	mov rax, 0 # syscall for read
	mov rdi, 0 # file handle 0 is stdin
	mov rsi, str # address of string to output
	mov rdx, 100 # number of bytes 
	syscall # invoke syscall to read 
# write
    mov rsi, rax # moving the syscall result
    mov rax, 1 # syscall for write
	mov rdi, 1 # file handle 1 is stdout
	# lea rsi, str # address of string to output
	mov rdx, 100 # number of bytes 
	syscall # invoke syscall to write 
# exit
	mov rax, 60 # syscall for exit
	xor rdi, rdi # exit code 0
	syscall # invoke syscall to exit 

# https://stackoverflow.com/questions/23468176/read-and-print-user-input-with-x86-assembly-gnu-linux

message: .ascii "Hello world!\n\0" # note the newline at the end



