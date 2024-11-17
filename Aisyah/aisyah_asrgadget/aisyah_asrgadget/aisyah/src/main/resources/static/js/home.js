const urlData = new URLSearchParams(window.location.search);
if (localStorage.length === 0) {
  const id = urlData.get("id");
  const email = urlData.get("email");
  const name = urlData.get("name");
  const role = urlData.get("role");
  localStorage.setItem("id", id);
  localStorage.setItem("email", email);
  localStorage.setItem("name", name);
  localStorage.setItem("role", role);
  document.getElementById("login").textContent =
    "Hai " + localStorage.getItem("name");
  document.getElementById("login").setAttribute("href", "#");
  if (localStorage.getItem("role") == "null") {
    const buy = document.createElement("a");
    buy.textContent = "Buy";
    buy.setAttribute("href", "buy");
    document.querySelector(".navbar").appendChild(buy);
  } else {
    const dashboard = document.createElement("a");
    dashboard.textContent = "Dashboard";
    dashboard.setAttribute("href", "dashboard");
    document.querySelector(".navbar").appendChild(dashboard);
  }
  const logout = document.createElement("a");
  logout.textContent = "Logout";
  logout.setAttribute("href", "login");
  logout.addEventListener("click", () => {
    localStorage.clear();
  });
  document.querySelector(".navbar").appendChild(logout);
} else {
  document.getElementById("login").textContent =
    "Hai " + localStorage.getItem("name");
  document.getElementById("login").setAttribute("href", "#");
  if (localStorage.getItem("role") == "null") {
    const buy = document.createElement("a");
    buy.textContent = "Buy";
    buy.setAttribute("href", "buy");
    document.querySelector(".navbar").appendChild(buy);
  } else {
    const dashboard = document.createElement("a");
    dashboard.textContent = "Dashboard";
    dashboard.setAttribute("href", "dashboard");
    document.querySelector(".navbar").appendChild(dashboard);
  }
  const logout = document.createElement("a");
  logout.textContent = "Logout";
  logout.setAttribute("href", "login");
  logout.addEventListener("click", () => {
    localStorage.clear();
  });
  document.querySelector(".navbar").appendChild(logout);
}
