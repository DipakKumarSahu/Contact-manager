console.log("Welcome to admin.js ");

// Image preview code start
document.querySelector("#iamge_file_input").addEventListener("change", function (event) {
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.onload = function () {
        document.querySelector("#upload_image_preview").setAttribute("src",reader.result);
    };
    reader.readAsDataURL(file);
    
});
// image preview code end