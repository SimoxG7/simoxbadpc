section .data
	str db 100b 

section .text
	global _start

_start:
	mov rax, 1
	mov rdi, 1
	mov     rsi, msg
	mov     rdx, 13
	syscall
	mov    rax, 60
	mov    rdi, 0
	syscallù

