document.addEventListener('DOMContentLoaded', function () {
    const toastElement = document.getElementById('feedback-toast');
    if (toastElement) {
        setTimeout(() => {
            toastElement.style.display = 'none';
        }, 5000); // Ocultar después de 5 segundos
    }
});