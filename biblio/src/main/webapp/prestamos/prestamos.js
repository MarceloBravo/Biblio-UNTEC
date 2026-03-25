
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
        if(formId === "loanForm"){ //Se está creando o actualizando un prestamo
            const validate = validaDatos();
            if(typeof validate === "string"){
                mostrarError(validate);
                return;
            }
        }
        document.getElementById(formId).submit();
    });
}

const mostrarError = (mensaje) => {
    const alert = document.getElementById("feedback-alert-js");
    alert.innerHTML = mensaje;
    alert.style.display = "block";
    setTimeout(() => {
        alert.style.display = "none";
    }, 3000);
}

const validaDatos = () => {
    const bookId = document.getElementById("input-book_id");
    const userId = document.getElementById("input-user_id");
    const fechaPrestamo = document.getElementById("input-fechaPrestamo");
    const fechaDevolucion = document.getElementById("input-fechaDevolucion");
    const fechaRetorno = document.getElementById("input-fechaRetorno");

    if(bookId === undefined || bookId.value === "" || userId === undefined || userId.value === "" || fechaPrestamo === undefined || fechaPrestamo.value === "" || fechaDevolucion === undefined || fechaDevolucion.value === ""){
        return "Datos incompletos";
    }
    if(fechaPrestamo.value > fechaDevolucion.value){
        return "Datos no válidos: La fecha de Prestamo debe ser menor a las fechas de devolución";
    }
    if(fechaRetorno !== undefined && fechaRetorno.value !== "" && fechaPrestamo.value > fechaRetorno.value){
        return "Datos no válidos: La fecha de Prestamo debe ser menor a las fechas de devolución";
    }
    return true;
}

const showFindUserModal = () => {
    document.getElementById('tb-users-table-search').innerHTML = '';
    document.getElementById('input-userSearch').value = '';
    const userModal = document.getElementById("findUserModal");
    const miModal = new bootstrap.Modal(userModal);
    miModal.show();
}


const inputUserSearchKeyDown = (e) => {
    if(e.key !== "Enter" && e.blur === false){        
        return; 
    }    
    const inputUserSearch = document.getElementById("input-userSearch");
    const searchValue = inputUserSearch.value ? "&search=" + inputUserSearch.value : "";

    if(inputUserSearch.value === ""){
        document.getElementById('tb-users-table-search').innerHTML = '';
        return;
    }
 
    
    fetch('/users?format=json'+searchValue)
    .then(response => response.json())
    .then(usuarios => {
        const tablaBody = document.getElementById('tb-users-table-search');
        tablaBody.innerHTML = '';
        usuarios.forEach(u => {
            tablaBody.innerHTML += `
            <tr class='tr-select' onclick="seleccionarUsuario( ${u.id}, '${u.nombre} ${u.apellidos}')">
                <td>${u.nombre}</td>
                <td>${u.apellidos}</td>
                <td>${u.email}</td>
            </tr>
            `
        });
    })
    .catch(error => {
        console.log(error);
    });
}


const seleccionarUsuario = (id, nombre) => {
    // Llenar los campos del formulario principal
    document.getElementById('input-user_id').value = id;
    document.getElementById('input-user_name').value = nombre;
    
    // Cerrar el modal
    const modal = bootstrap.Modal.getInstance(document.getElementById('findUserModal'));
    modal.hide();
}




const showFindBookModal = () => {
    document.getElementById('tb-books-table-search').innerHTML = '';
    document.getElementById('input-bookSearch').value = '';
    const bookModal = document.getElementById("findBookModal");
    const miModal = new bootstrap.Modal(bookModal);
    miModal.show();
}


const inputBookSearchKeyDown = (e) => {
    if(e.key !== "Enter" && e.blur === false){        
        return; 
    }    
    const inputBookSearch = document.getElementById("input-bookSearch");
    const searchValue = inputBookSearch.value ? "&search=" + inputBookSearch.value : "";

    if(inputBookSearch.value === ""){
        document.getElementById('tb-books-table-search').innerHTML = '';
        return;
    }
 
    
    fetch('/books?format=json'+searchValue)
    .then(response => response.json())
    .then(usuarios => {
        const tablaBody = document.getElementById('tb-books-table-search');
        tablaBody.innerHTML = '';
        usuarios.forEach(l => {
            tablaBody.innerHTML += `
            <tr class='tr-select' onclick="seleccionarLibro( ${l.id}, '${l.nombre}', '${l.autor}', '${l.editorial}', '${l.isbn}', '${l.fecha_publicacion}')">
                <td>${l.isbn}</td>
                <td>${l.nombre}</td>
                <td>${l.autor}</td>
                <td>${l.editorial}</td>
            </tr>
            `
        });
    })
    .catch(error => {
        console.log(error);
    });
}


const seleccionarLibro = (id, nombre, autor, editorial, isbn, fechaPublicacion) => {
    console.log("ID = ", id, "NOMBRE = ", nombre, "AUTOR = ", autor, "EDITORIAL = ",editorial, "ISBN = ",isbn)
    // Llenar los campos del formulario principal
    document.getElementById('input-isbn').value = isbn;
    document.getElementById('input-book_id').value = id;
    document.getElementById('input-book_name').value = nombre;
    document.getElementById('input-editorial').value = editorial;
    document.getElementById('input-fechaPublicacion').value = fechaPublicacion;
    
    // Cerrar el modal
    const modal = bootstrap.Modal.getInstance(document.getElementById('findBookModal'));
    modal.hide();
}