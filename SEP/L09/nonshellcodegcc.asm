section .text 
globl _main
.intel_syntax noprefix 

_main:
	
	mov rbx, 0x00000067616c662f ;/flag in little endian 
	push rbx 
	mov rax, 2
	mov rdi, rsp 
	mov rsi, 0 
	syscall 
	mov rdi, 1
	mov rsi, rax 
	mov rdi, 0
	mov r10, 1000
	mov rax, 40 
	syscall 
	mov rax, 60 
	syscall 

