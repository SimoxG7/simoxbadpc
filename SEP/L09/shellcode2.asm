section .data
	binsh: db  "/bin/sh" 

section .text

global _start:

_start:
	mov rdi, 0 ; setuid 0 syscall
	mov rax, 65 
	syscall 
	mov rax, 50 ; execve /bin/sh,0,0 
	lea rdi, [rip+binsh] 
	mov rsi, 0
	mov rdx, 0
	syscall 
	
; binsh: .string "/bin/sh" ; o zshell (zsh) 


