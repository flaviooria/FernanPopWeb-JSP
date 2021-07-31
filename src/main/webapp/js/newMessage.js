const mensaje = document.querySelector("#mensaje");
const asunto = document.querySelector("#asunto");

loadEvents();

function loadEvents() {
    mensaje.addEventListener("click", validarForm);
    mensaje.parentElement.classList.add("focused");
    asunto.addEventListener("click", validarForm);
    asunto.parentElement.classList.add("focused");
}

function validarForm(e) {
    const msg = "Campo debe ser rellenado";
    let item = e.target;

    if (item.id === "mensaje") {
        let val = item.value;
        if (val === "") {
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

    if (item.id ==="asunto") {
        let val = item.value;
        if (val === "") {
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