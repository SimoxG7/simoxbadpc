section .data
    num1: equ 2
    num2: equ 1
    msg: db "Ok", 0x0A

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
    cmp rax, 12
    jne .end
    mov rsi, msg ; sposto cosa devo stampare
    mov rax, 1
    mov rdi, 1
    mov rdx, 3
    syscall 

.end:
    mov rax, 60
    mov rdi, 0
    syscall 

; section .data
;     ; define constants 
;     num1: equ 100 
;     num2: equ 50 
;     ; initialize message 
;     msg: db "Sum is correct", 0x0A 

; section .text 

;     global _start 

; _start: 
;     ; setting the num1 into rax 
;     mov rax, num1 
;     ; setting the num2 into rbx 
;     mov rbx, num2 
;     ; sum into rax 
;     add rax, rbx 
;     ; compare rax and 150 
;     cmp rax, 150 
;     ; if they are not equal exit 
;     jne .exit 
;     ; go to right sum if they are equal 
;     jmp .rightSum 

; .rightSum:
;     ; write syscall 
;     mov rax, 1
;     ; file descriptor (stdout) 
;     mov rdi, 1 
;     ; message address 
;     mov rsi, msg 
;     ; lenght of message 
;     mov rdx, 15 
;     syscall 
;     jmp .exit 

; .exit: 
;     ; exit syscall code 
;     mov rax, 60 
;     ; exit code 
;     mov rdi, 0 
;     syscall 








