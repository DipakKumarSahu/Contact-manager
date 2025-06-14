console.log("Hello I am Script page of your js folder")

let currentTheme = getTheme();

document.addEventListener('DOMContentLoaded', ()=>{
    changeTheme()
});



// TODO
function changeTheme(){
    //Set to web page
    document.querySelector("html").classList.add(currentTheme);

    //Set the listner to hcange theme button
    const changeThemeButton = document.querySelector("#theme_change_button");

    changeThemeButton.querySelector("span").textContent =
         currentTheme == 'light' ? 'Dark' : 'Light';
   
   

    changeThemeButton.addEventListener("click",(event) => {
      let oldTheme = currentTheme;
        if(currentTheme == "dark"){
           currentTheme="light";
        }
        else{
           currentTheme = "dark";
        }
        
        //Update in local storage
        setTheme(currentTheme);

        // remove current theme and add a new theme
        document.querySelector("html").classList.remove(oldTheme);
        document.querySelector("html").classList.add(currentTheme);

        //Change the text of Button
        changeThemeButton.querySelector("span").textContent =
         currentTheme == 'light' ? 'Dark' : 'Light';
         
      
    });
    
   
}


//Set Theme from Local Storage
function setTheme(theme){
    localStorage.setItem("theme", theme);
}


// Get Theme from Local Storage
function getTheme(){
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}

