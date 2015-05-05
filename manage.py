"""Module manage.

"""

import sys
import os
import subprocess
import launcher
import shutil

attributs = {
    'flags': '-g',# -Xlint:all',
    #'main_class': 'gui.GUI'
    'main_class': 'core.MainConsole'
    #'main_class': 'excel.Excel_settings'
}

### compile parts
def build(self):
    files_path = os.path.join('src', 'main')
    main_class = self.main_class.replace('.', os.sep) + '.java'

    cmd = '{} -d bin -sourcepath {} -cp "lib/poi-3.11/*" {} {}'.format(
            self.compilo, files_path,
            os.path.join(files_path, main_class), self.flags)

    subprocess.call(cmd, shell=True)


### run parts
def run(self, *args):
    subprocess.call('java -cp "lib/poi-3.11/*:bin" {} {}'.format(self.main_class, ' '.join(args)),
                        shell=True)


def clean(self):
    """clean the java project"""

    self._clean(os.path.join('bin', '*'),
                os.path.join('tree', '*'))

    self._clean_rec('*~')

launcher.main()
