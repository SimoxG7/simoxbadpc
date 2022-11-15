globl _main 
.intel_syntax noprefix 

_main: 

    mov rax, 1 
    mov rdi, 1 
    lea rsi, [rip + youwin]
    mov rdx, 20
    syscall
    mov rax, 60
    syscall 

youwin: 
    .string "you WIN!!\n"
    
