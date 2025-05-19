# 效果并不好，先别用了

from pdf2docx import Converter

# Input and output file paths
pdf_file = 'report.pdf'
docx_file = '机器学习2025作业2_陈墨霏_1120233329.docx'

# Convert PDF to DOCX
def pdf_to_docx(pdf_path, docx_path):
    cv = Converter(pdf_path)
    cv.convert(docx_path, start=0, end=None)  # Convert all pages
    cv.close()
    print(f"Conversion complete: {docx_path}")

# Perform the conversion
pdf_to_docx(pdf_file, docx_file)