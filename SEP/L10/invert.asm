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
;     sub rbx, 8
;     push [rbx]
;     inc rcx
;     jmp .loop1


; .loop2: 
;     cmp rcx, 0
;     je .print
;     push qword [rbx]
;     add rbx, 8
;     dec rcx
;     jmp .loop2

; .print:
;     cmp rcx, 0
;     je .end
;     pop rsi
;     add rsp, 8
;     mov rax, 1
;     mov rdi, 1
;     mov rdx, 3
;     syscall
;     inc rcx
;     jmp .end 

; .end:
;     mov rax, 60
;     mov rdi, 0
;     syscall 

global _start
    
section .text
    
_start:
        lea     rax, hello
    
        xor     rcx, rcx
.startLoop1:
        mov     rbx, rcx
        add     rbx, rax
        push    [rbx]
        cmp     rcx, 11
        je      .endLoop1
        inc rcx
        jmp     .startLoop1
.endLoop1:
    
        xor     rcx, rcx
.startLoop2:
        mov     rbx, rcx
        add     rbx, rax
        pop     [rbx]
        cmp     rcx, 11
        je      .endLoop2
        inc     rcx
        jmp     .startLoop2
.endLoop2:
    
        ; write message
        mov     rsi, rax
        mov     rax, 1
        mov     rdi, 1
        lea     rsi, hello
        mov     rdx, 12         ; lunghezza
        syscall
    
.exit:
        mov     rax, 60
        xor     rdi, rdi
        syscall
    
section .data 
        hello:  .ascii "hello world!"

