import os
from os import path
import sys
from subprocess import call, Popen
import shutil
import inspect

### Options
dirs_to_clean = ['bin', 'docs', 'tree']



### compile parts
def make():
    call(['javac -d bin -sourcepath src src/main/core/ExcelLent.java'], shell=True)
    #call(['javac -d bin -sourcepath src -cp "lib/*" src/com/sample/DroolsTest.java'], shell=True)

### configure parts
def configure():
    print('hello')

### test & analysze parts
def test():
    print('hello')

### doc parts
def doc():
    print('hello')

### run parts
def run():
    os.chdir('bin')
    call(['java main.core.ExcelLent'], shell=True)
    #call(['java -cp "bin:lib/*:." com.sample.DroolsTest'], shell=True)


### clean parts
def clean():
    root_project = path.dirname(path.abspath(inspect.getsourcefile(clean)))
    
    for dir_to_clean in dirs_to_clean:
        rm_dir(path.join(root_project, dir_to_clean))


def rm_dir(path):
    for root, dirs, files in os.walk(path):
        for f in files:
            os.unlink(os.path.join(root, f))
        for d in dirs:
            shutil.rmtree(os.path.join(root, d))
            

### The operations
operations = {
    'make' :        (make, '\t\tcompile the software'),
    'configure':    (configure, '\tconfigure it to adapt it at the architecture'),
    'test':         (test, '\t\trun the unit test and integration test'),
    'doc':          (doc, '\t\tcreate the doc'),
    'run':          (run, '\t\texecute the software'),
    'clean':        (clean, '\t\tclean the project'),
}


if __name__ == "__main__":
    opt = ['make', 'run']
    
    if len(sys.argv) > 1:
        opt = sys.argv[1:]
    
    if 'help' in opt:
        for cmd_name, (stage, help) in operations.items():
            print(cmd_name + ': ' + help)
    else:
        for stage in opt:
            if not stage in operations:
                print('The operation ' + stage + ' doesn\'t exists')
            else:
                current_stage = operations[stage][0]
                current_stage()