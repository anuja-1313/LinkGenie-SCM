console.log("Script loaded");

let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", () => {
    changeTheme(currentTheme);
});

// Function to change theme
function changeTheme(currentTheme) {

    document.querySelector("html").classList.add(currentTheme);

    const changeThemeButton = document.querySelector('#theme_change_button');
    changeThemeButton.addEventListener("click", (event) => {
        console.log("Change theme button clicked");

        const oldTheme = currentTheme;

        // Toggle theme between light and dark
        currentTheme = currentTheme === "dark" ? "light" : "dark";

        // Update theme in localStorage
        setTheme(currentTheme);
        document.querySelector('html').classList.remove(oldTheme);
        document.querySelector('html').classList.add(currentTheme);
    });
}

// Set theme to localStorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

// Get theme from localStorage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";  // Default to 'light' if no theme is found
}
