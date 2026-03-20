
const eliminarClick = (id) => {
    const modal = document.getElementsByClassName("modal")[0];
    modal.style.display = "block";
    document.getElementById("modal-title").innerHTML = "Eliminar registro";
    document.getElementById("modal-message").innerHTML = "¿Desea eliminar el registro?";
    document.getElementById("btn-modal-aceptar").innerHTML = "Eliminar";
    document.getElementById("btn-modal-cancelar").innerHTML = "Cancelar";
    
    
    const btnAceptar = document.getElementById("btn-modal-aceptar");
    btnAceptar.addEventListener("click", (e) => {
        modal.style.display = "none";
        document.getElementById("delete-id").value = id;
        document.getElementById("delete-form").submit();
    });
}