from PIL import Image

# List of image filenames
image_files = ["B_0.png", "B_1.png", "B_2.png"]

# Desired crop dimensions
crop_width, crop_height = 310, 250

for file in image_files:
    # Open the image
    img = Image.open(file)
    
    # Calculate the center crop box
    width, height = img.size
    left = (width - crop_width) // 2
    top = (height - crop_height) // 2
    right = left + crop_width
    bottom = top + crop_height
    
    # Crop the image
    cropped_img = img.crop((left, top, right, bottom))
    
    # Save the cropped image
    cropped_img.save(file)
    print(f"Cropped and saved: {file}")