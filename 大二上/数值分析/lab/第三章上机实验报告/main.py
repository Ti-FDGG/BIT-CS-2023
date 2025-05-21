import sys
from GUI.GUI_main import *


app = QApplication(sys.argv)
window = MainWindow()
window.show()
sys.exit(app.exec())
