; section .data
;     str: db "Simox"

; section .text 
; global _start 

; _start: 
;     lea rbx, str ; metto in rbx l'indirizzo di memoria della stringa 
;     xor rcx, rcx ; uso rcx come contatore (azzerandolo con xor)
;     jmp .loop1

; .loop1: 
;     cmp rcx, 5
;     je .loop2
;     push qword [rbx]
;     sub rbx, 1
;     inc rcx
;     jmp .loop1


; .loop2: 
;     cmp rcx, 0
;     je .print
;     pop rbx
;     add rbx, 1
;     dec rcx
;     jmp .loop2

; .print:
;     mov rsi, rbx
;     mov rax, 1
;     mov rdi, 1
;     mov rdx, 5
;     syscall
;     jmp .end 

; .end:
;     mov rax, 60
;     mov rdi, 0
;     syscall 

section .data
    str: db "Simox"

section .text 
global _start 


_start:
            lea     rax, str
     
            xor     rcx, rcx
    startLoop1:
            mov     rbx, rcx
            add     rbx, rax
            push    qword [rbx]
            cmp     rcx, 5
            je      endLoop1
            inc rcx
            jmp     startLoop1
    endLoop1:
     
            xor     rcx, rcx
    startLoop2:
            mov     rbx, rcx
            add     rbx, rax
            pop     qword [rbx]
            cmp     rcx, 5
            je      endLoop2
            inc     rcx
            jmp     startLoop2
    endLoop2:
     
            ; write message
            mov     rsi, rax
            mov     rax, 1
            mov     rdi, 1
            lea     rsi, str
            mov     rdx, 6         ; lunghezza
            syscall
     
    exit:
            mov     rax, 60
            xor     rdi, rdi
            syscall