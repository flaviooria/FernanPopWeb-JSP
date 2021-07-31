const btnSend = document.querySelector(".btn-save");
const desc = document.querySelector("#descrip");
const title = document.querySelector("#name");
const price = document.querySelector("#price");
const img = document.querySelector("#img-file");
const input = document.querySelectorAll("input");


loadApp()
loadEvents();

//Cargar la pagina el boton esta desactivado
function loadApp() {
    btnSend.setAttribute("disabled", "")
    btnSend.style.cursor = "not-allowed";
    btnSend.style.opacity = "0.3";
}

function loadEvents() {
    desc.addEventListener("click", validarForm);
    title.addEventListener("click", validarForm);
    price.addEventListener("click", validarForm);
    //Img producto
    img.addEventListener("change", cargarAvartarUser)

    desc.parentElement.classList.add("focused");
    title.parentElement.classList.add("focused");
    price.parentElement.classList.add("focused");
}

function cargarAvartarUser(e) {
    //Obtengo el archivo que cargo
    let img = e.target.files[0];
    //Creo un flujo para leer el fichero
    let lectorImg = new FileReader();
    //Leo el fichero
    lectorImg.addEventListener("load", (e) => {
        //Muestro imagen en 
        let imgSrc = e.target.result;
        //la etiqueta img para pintarla
        document.querySelector(".product-img").src = imgSrc;

        //Obtengo la extensiíon de la imagen que cargo.
        let typeFile = document.querySelector(".img-file").value
        console.log(typeFile);

        if (typeFile.includes("jpg")) {
            typeFile = "jpg";
        } else if (typeFile.includes("jpeg")) {
            typeFile = "jpeg";
        } else {
            typeFile = "png";
        }

        document.querySelector(".extImg").setAttribute("value", typeFile);

    })

    if (img != null) {
        lectorImg.readAsDataURL(img);
    }

}

function validarForm(e) {
    const msg = "Campo debe ser rellenado";
    let item = e.target;

    if (item.id == "descrip") {
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
    if (item.id == "name") {
        item.addEventListener("blur", () => {
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
        })
    }
    if (item.id == "price") {
        item.addEventListener("blur", () => {
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
            }
        })
    }
}

//Valida form
window.addEventListener("click",() => {
    if (title.value != "" && desc.value != "" && !(isNaN(parseInt(price.value))) && img.value != "") {
        btnSend.removeAttribute("disabled", "");
        btnSend.style.cursor = "pointer";
        btnSend.style.opacity = "1";
        console.log("entro");
    } else {
        btnSend.setAttribute("disabled", "")
        btnSend.style.cursor = "not-allowed";
        btnSend.style.opacity = "0.3";
        console.log("no valido");
    }
})

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