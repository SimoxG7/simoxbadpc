.intel_syntax noprefix

section .text
global _start:

_start:
	mov rax, 59
	lea rdi, [rip+binsh]
	mov rsi, 0 
	mov rdx, 0
	syscall

binsh:
.string "/bin/sh"


