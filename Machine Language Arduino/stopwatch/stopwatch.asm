.include "m328Pdef.inc"

.equ F_CPU = 16000000
.equ Baud = 9600
.equ myBaud = F_CPU/(16*Baud)-1

.org 0
rjmp main

.org 0x0002
rjmp main
.org 0x0004
rjmp main
.org 0x0006
rjmp main
.org 0x0008
rjmp main
.org 0x000a
rjmp main
.org 0x000c
rjmp main
.org 0x000e
rjmp main
.org 0x0010
rjmp main
.org 0x0012
rjmp main
.org 0x0014
rjmp main
.org 0x0016
rjmp main
.org 0x0018
rjmp main
.org 0x001a
rjmp main
.org 0x001c
rjmp main
.org 0x001e
rjmp main
.org 0x0020
rjmp main
.org 0x0022
rjmp main
.org 0x0024
rjmp main
.org 0x0026
rjmp main
.org 0x0028
rjmp main
.org 0x002a
rjmp main
.org 0x002c
rjmp main
.org 0x002e
rjmp main
.org 0x0030
rjmp main
.org 0x0032
rjmp main

main:

ldi r16, low(RAMEND)
out spl, r16
ldi r16, high(RAMEND)
out sph, r16

ldi zl, low(string<<1)
ldi zh, high(string<<1)

ldi r16, low(myBaud)
sts UBRR0L, r16
ldi r16, high(myBaud)
sts UBRR0H, r16

ldi r16, 128
sts TCNT0, r16

ldi r16, (1<<CS01)
sts TCCR0B, r16

ldi r16, (1<<RXEN0 | 1<<TXEN0)| (1<<RXCIE0)
sts UCSR0B, r16

ldi r16, (1<<UCSZ01) | (1<<UCSZ00)
sts UCSR0C, r16

sei

string_loop:

	lpm r17, z+
	cpi r17, 0
	breq here
	rcall UART_TX
	sts UDR0, r17
	;ldi r16,(1<<TXEN0) | (1<<RXEN0)
	;sts UCSR0B, R16
	rjmp string_loop

here:
	rjmp here

UART_TX:
	lds r16, UCSR0A
	SBRS r16, UDRE0
	rjmp UART_TX
	sts UDR0, r17
	ret

RX_ISR:
	push r17
	in r17, sreg
	push r17
	lds r17, UDR0
	call UART_TX
	pop r17
	out sreg, r17
	pop r17
	reti

string: .db "Press any key to start the clock. ", 0
