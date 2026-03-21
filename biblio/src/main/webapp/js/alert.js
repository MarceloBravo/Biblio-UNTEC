document.addEventListener('DOMContentLoaded', function () {
    const alertElement = document.getElementById('feedback-alert');
    if (alertElement) {
        setTimeout(() => {
            alertElement.style.display = 'none';
        }, 10000);
    }
});