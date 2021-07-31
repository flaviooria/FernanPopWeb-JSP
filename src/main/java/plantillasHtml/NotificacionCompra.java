package plantillasHtml;

public class NotificacionCompra {

        public static String obtenerHtml(String nombreComprador, String nombreArticulo,String nombreVendedor, double precio) throws NullPointerException {
            return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                    " \n" +
                    "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                    " \n" +
                    " <head>\n" +
                    " \n" +
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                    " \n" +
                    "<title>Compra Realizada</title>\n" +
                    " \n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
                    " \n" +
                    "</head>\n" +
                    "<body style=\"font-family: 'Quicksand', sans-serif; background: linear-gradient(to bottom, rgba(85,241,241,1) 0%, rgba(151,192,216,1) 46%, rgba(151,192,216,1) 69%, rgba(85,241,241,1) 100%);\">\n" +
                    "    <table style=\"max-width: 600px; padding: 10px; margin: 0 auto;border-collapse: collapse;\" border=\"0\">\n" +
                    "        <tr>\n" +
                    "            <td style=\"text-align: center;\">\n" +
                    "                <h1 class=\"title\"><a href=\"https://flaviooria.github.io/FernanPop-web/\" target=\"_blank\" style=\"\n" +
                    "                    text-decoration: none;\n" +
                    "                    color: #293241;\n" +
                    "                    font-family: 'Raleway', sans-serif;\n" +
                    "                    font-size: 1.5em;\">\n" +
                    "                    FernanPop\n" +
                    "                </a>\n" +
                    "                </h1>\n" +
                    "            </td>\n" +
                    "        </tr>\n" +
                    "        <tr>\n" +
                    "            <td style=\"padding: 1em;\">\n" +
                    "            <h3 style=\"\n" +
                    "                text-align: center;\n" +
                    "                color:#293241;\n" +
                    "                font-family: 'Raleway', sans-serif;\n" +
                    "                font-size: 1.5em;\">\n" +
                    "                Enhorabuena acabas de realizar tu compra.\n" +
                    "            </h3>\n" +
                    "            </td>\n" +
                    "        </tr>\n" +
                    "        <tr>\n" +
                    "            <td style=\"padding: 1em;\"><p style=\" \n" +
                    "                font-family:'Quicksand', sans-serif;\n" +
                    "                color: #293241;text-align: justify;\">Hola "+ nombreComprador+",<br>Tu compra ha sido realizada satisfactoriamente, ahora solo queda que valores al vendedor.</p></td>\n" +
                    "        </tr>\n" +
                    "        <tr>\n" +
                    "            <td style=\"padding: 1em;\">\n" +
                    "                <p style=\" \n" +
                    "                margin-top: 5px;\n" +
                    "                font-family: 'Quicksand', sans-serif;\n" +
                    "                color: #293241;\">Gracias por tu confianza.</p>\n" +
                    "            </td>\n" +
                    "            <tr>\n" +
                    "                <td style=\"\n" +
                    "                background-color: #3D5A80;\n" +
                    "                height: 100px;\n" +
                    "                border-radius: 10px;\n" +
                    "                margin:30px;\n" +
                    "                border:none;\">\n" +
                    "                    <p style=\"\n" +
                    "                    color: #293241;\n" +
                    "                    font-family: 'Raleway', sans-serif;\n" +
                    "                    font-weight: 700;\n" +
                    "                    font-size: 1.5em;\n" +
                    "                    text-align: center;\">\n" +
                    "                    <span style=\"color: #EE6C4D;\"> Artículo: </span>"+ nombreArticulo +"<br>\n" +
                    "                    <span style=\"color: #EE6C4D;\"> Vendedor: </span>"+ nombreVendedor +"<br>\n" +
                    "                    <span style=\"color: #EE6C4D;\"> Precio: </span>"+ precio +"<br>\n" +
                    "                </td>\n" +
                    "            </tr>\n" +
                    "        </tr>\n" +
                    "        <tr>\n" +
                    "            <td style=\"padding: 1em;\">\n" +
                    "                <p style=\"\n" +
                    "                margin-top: 10px;\n" +
                    "                text-align: center;\n" +
                    "                color: #293241;\n" +
                    "                font-size: 1.2em;\n" +
                    "                font-weight: 500;\">\n" +
                    "                Tus amigos de FernanPop  <i class=\"fas fa-heart\" style=\"color:#EE6C4D ;\"></i>\n" +
                    "            </p>\n" +
                    "            </td>\n" +
                    "        </tr>\n" +
                    "        <tr>\n" +
                    "            <td style=\"padding: 1em;\">\n" +
                    "                <p style=\"\n" +
                    "                text-align: center;\n" +
                    "                margin-top: 10px;\">© 2021 FernanPop Web - Martos, Jaén, España</p>\n" +
                    "            </td>\n" +
                    "            <tr>\n" +
                    "                <td style=\"padding: 1em;\">\n" +
                    "                    <ul class=\"about-media\" style=\"display: flex;justify-content: center;align-items: center;\">\n" +
                    "                        <li style=\"margin: 0 10px;\n" +
                    "                        list-style: none;\n" +
                    "                        font-size: 2em;\"><a href=\"\" title=\"facebook/fernanpop.com\" style=\"color: #293241;\"><i class=\"fab fa-facebook-square\"></i></a></li>\n" +
                    "                        <li style=\"margin: 0 10px;\n" +
                    "                        list-style: none;\n" +
                    "                        font-size: 2em;\"><a href=\"\"><i class=\"fab fa-instagram-square\" style=\"color: #293241;\"></i></a></li>\n" +
                    "                        <li style=\"margin: 0 10px;\n" +
                    "                        list-style: none;\n" +
                    "                        font-size: 2em;\"><a href=\"\"><i class=\"fab fa-twitter-square\" style=\"color: #293241;\"></i></a></li>\n" +
                    "                        <li style=\"margin: 0 10px;\n" +
                    "                        list-style: none;\n" +
                    "                        font-size: 2em;\"><a href=\"https://github.com/flaviooria/FernanPop-web\" target=\"_blank\" style=\"color: #293241;\"><i class=\"fab fa-github-square\"></i></a></li>\n" +
                    "                    </ul>\n" +
                    "                </td>\n" +
                    "            </tr>\n" +
                    "        </tr>\n" +
                    "    </table>\n" +
                    "</body>\n" +
                    "</html>";
        }
}
