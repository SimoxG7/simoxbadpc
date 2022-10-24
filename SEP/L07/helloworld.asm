global _start
	section.text
_start: 
	mov rax, 1 ; syscall for write
	mov rdi, 1 ; file handle 1 is stdout 
	lea rsi, message ; carico
