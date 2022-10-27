section .data
    num1: equ 2
    num2: equ 1

section .text 
global _start 

_start:
    mov rax, num1 
    mov rbx, num2
    mov rcx, 0 
    jmp .loop

.loop:
    cmp rcx, 10
    je .loop_end
    add rax, rbx
    inc rcx 
    jmp .loop 

.loop_end:
    mov rsi, rax ; sposto cosa devo stampare
    mov rax, 1
    mov rdi, 1
    mov rdx, 100
    syscall 

    mov rax, 60
    mov rdi, 0
    syscall 
