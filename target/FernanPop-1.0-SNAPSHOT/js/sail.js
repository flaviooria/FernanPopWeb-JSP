const comment = document.querySelector("#commentary");
const point = document.querySelector("#punctuation");
const price = document.querySelector("#price");
const input = document.querySelectorAll("input");
const price_custom = document.querySelector(".price-custom");

//loadApp()
loadEvents();

function loadEvents() {
    comment.addEventListener("click", validarForm);
    point.addEventListener("click", validarForm);
    price.addEventListener("click", validarForm);


    comment.parentElement.classList.add("focused");
    point.parentElement.classList.add("focused");
    price.parentElement.classList.add("focused");
}

function validarForm(e) {
    const msg = "Campo debe ser rellenado";
    let item = e.target;

    if (item.id == "commentary") {
        let val = item.value;
        if (val == "") {
            item.classList.remove("filled");
            item.classList.add("filled-danger");
            item.setAttribute("placeholder", msg);
            if (item.getAttribute("placeholder")) {
                item.parentElement.classList.add("focused");
            }
            setTimeout(() => {
                item.classList.remove("filled-danger");
                item.removeAttribute("placeholder");
            }, 1000);
        } else {
            item.classList.remove("filled-danger");
            item.removeAttribute("placeholder");
            item.classList.add("filled");
        }

    }
    if (item.id == "punctuation") {
        item.addEventListener("click", () => {
            let val = item.value;
            if (val == "" || isNaN(parseInt(val)) || isNaN(parseFloat(val))) {
                item.classList.remove("filled");
                item.classList.add("filled-danger");
                item.setAttribute("placeholder", "Debes ingresar un número");
                if (item.getAttribute("placeholder")) {
                    item.parentElement.classList.add("focused");
                    item.value = "";
                    item.setAttribute("placeholder", "Puntuación del 1 al 5");
                }
                setTimeout(() => {
                    item.classList.remove("filled-danger");
                    item.removeAttribute("placeholder");
                    item.value = "";
                }, 1000);
            } else {
                item.classList.remove("filled-danger");
                item.removeAttribute("placeholder");
                item.classList.add("filled");
            }
        })
    }
    if (item.id == "price") {
        item.addEventListener("click", () => {
            let val = item.value;
            if (val == "" || isNaN(parseInt(val)) || isNaN(parseFloat(val))) {
                item.classList.remove("filled");
                item.classList.add("filled-danger");
                item.setAttribute("placeholder", "Debes ingresar un número");
                if (item.getAttribute("placeholder")) {
                    item.parentElement.classList.add("focused");
                    item.value = "";
                    item.setAttribute("placeholder", "Debes ingresar un número");
                }
                setTimeout(() => {
                    item.classList.remove("filled-danger");
                    item.removeAttribute("placeholder");
                    item.value = "";
                }, 1000);
            } else {
                item.classList.remove("filled-danger");
                item.removeAttribute("placeholder");
                item.classList.add("filled");
                price_custom.innerHTML = val + " €";
            }
        })
    }
}

//Menu hamburguesa
loadHamburger();

function loadHamburger() {
    let hamburger = document.querySelector(".hamburger--spin");

    let menu = document.querySelector(".navbar-menu");

    hamburger.addEventListener("click", () => {
        hamburger.classList.toggle("is-active");
        menu.classList.toggle("open");
    })
}