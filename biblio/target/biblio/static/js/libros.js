
const showModal = (formId, id = null, title = "Información", message = "", btnAceptarText = "Aceptar", btnCancelarText = "Cancelar") => {
    const modal = document.getElementsByClassName("modal")[0];
    modal.style.display = "block";
    document.getElementById("modal-title").innerHTML =title;
    document.getElementById("modal-message").innerHTML = message;
    document.getElementById("btn-modal-aceptar").innerHTML = btnAceptarText;
    document.getElementById("btn-modal-cancelar").innerHTML = btnCancelarText;
    
    
    const btnAceptar = document.getElementById("btn-modal-aceptar");
    btnAceptar.addEventListener("click", (e) => {
        modal.style.display = "none";
        if(id){
            document.getElementById("delete-id").value = id;
        }
        document.getElementById(formId).submit();
    });
}