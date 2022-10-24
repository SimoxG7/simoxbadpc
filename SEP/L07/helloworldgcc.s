.intel_syntax noprefix 
.global _start 

.section .text

_start: 
	mov rax, 1 # syscall for write
	mov rdi, 1 # file handle 1 is stdout
	lea rsi, message # address of string to output
	mov rdx, 13 # number of bytes 
	syscall # invoke syscall to write 

#write 

	mov rax, 60 # syscall for exit
	xor rdi, rdi # exit code 0
	syscall # invoke syscall to exit 

.section .data
message: .ascii "Hello world!\n\0" # note the newline at the end


