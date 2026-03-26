const btnCancelar = document.getElementById("btn-modal-cancelar");

btnCancelar.addEventListener("click", (e) => {
    const modal = document.getElementsByClassName("modal")[0];
    modal.style.display = "none";
});