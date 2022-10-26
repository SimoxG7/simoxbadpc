section .data 
    msg db "Hello World!", 0x0A ; constant msg + newline (counts as 1)

section .text 
    global _start

_start: ; size_t sys_write(unsigned int fd, const char * buf, size_t count); equivalente
    mov rax, 1 ; 1 in rax for write syscall 
    mov rdi, 1 ; 1st argument of syscall (stdout)
    mov rsi, msg ; pointer to 2nd argument of syscall 
    mov rdx, 13 ; 3rd argument of syscall (buffersize?)
    syscall 
    mov rax, 60 ; number of exit syscall
    mov rdi, 0 ; finish with error code 0 
    syscall ; build: nasm -f elf64 -o hello.o hello.asm # $ ld -o hello hello.o


















