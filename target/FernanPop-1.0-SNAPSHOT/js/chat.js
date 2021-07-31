let hamburger = document.querySelector(".hamburger--spin");

let menu = document.querySelector(".navbar-menu");

hamburger.addEventListener("click", () => {
    hamburger.classList.toggle("is-active");
    menu.classList.toggle("open");
})


let chat = document.querySelector(".content-chats .chat-item");
let button = document.querySelectorAll(".content .form button");
let label1 = document.querySelector(".tab1");
let label2 = document.querySelector(".tab2");
let line = document.querySelector(".line");
let msg_content = $(".new-message");
let contents = document.querySelector(".content-chats");
let chat_item_section = document.querySelector(".chat-item-section .chat-item");

button.forEach(item => {
    // Evento para cada boton
    item.addEventListener("click", () => {
        let windowWidth = window.innerWidth;

        //Cambia los valores del primer label
        label1.classList.add("return");
        label1.children[0].remove();
        let i = document.createElement("i");
        i.classList.add("fas");
        i.classList.add("fa-arrow-left");
        label1.textContent = "";
        label1.appendChild(i);
        label1.append("Volver");
        label2.classList.add("left");
        line.style.left = "0";

        document.querySelectorAll(".content").forEach(item => {
            //si da click el contenido de mensajes se mueve hacia un lado
            item.classList.add("left");
        })

        //Si el primer label tiene la clase
        if (label1.classList[1] === "return") {
            label1.addEventListener("click", () => {
                //Elimino la clase
                label1.classList.remove("return");

                document.querySelectorAll(".content").forEach(item => {
                    //Si cada item del content tiene la clase left se elimina 
                    if (item.className.includes("left")) {
                        item.classList.remove("left");
                        //Seteamos lo valores del chat-item a vacio
                        chat.innerHTML = "";
                        //Modificamos los labels y el line
                        line.removeAttribute("style");
                        label2.classList.remove("left");
                        label1.children[0].remove();
                        let i = document.createElement("i");
                        i.classList.add("fas");
                        i.classList.add("fa-envelope-open");
                        label1.textContent = "";
                        label1.appendChild(i);
                        label1.append(" Recibidos");
                    }
                })
            })
        }
    })
})

//Efectos del boton de nuevo mensaje


msg_content.click(function () {
    let msg = $(".go-to-message");
    if (msg_content.hasClass("new-message")) {
        msg_content.removeClass("new-message");
        msg_content.addClass("hover");
        if (msg.hasClass("none")) {
            msg.removeClass("none");
            msg.css("display", "block");
        }
    } else {
        msg_content.removeClass("hover");
        msg_content.addClass("new-message");
        msg.removeAttr("style");
        msg.addClass("none");
    }
})

