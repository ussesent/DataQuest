document.addEventListener("DOMContentLoaded", function() {
    const modal = document.getElementById("eventModal");
    const openBtn = document.getElementById("openModalBtn");
    const closeBtn = document.querySelector(".close");

    openBtn.addEventListener("click", () => modal.style.display = "block");
    closeBtn.addEventListener("click", () => modal.style.display = "none");
    window.addEventListener("click", (e) => {
        if (e.target === modal) modal.style.display = "none";
    });
});