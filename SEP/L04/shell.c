#include <stdio.h>

int main(int argc, char **argv) {
	int childPid;
	char* cmdLine;
	while(1) {
		printPrompt();
		cmdLine = readCommandLine();
		childPid = fork();
		if (childPid == 0) {
			exec (getCommand(cmdLine));
		} else {
			if (runInForeground(cmdLine)) {
				wait(childPid);
			} else { 
				recird in list of background jobs;
			}
		}
	}
}

