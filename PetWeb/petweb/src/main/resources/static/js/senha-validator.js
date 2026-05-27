const senhaInput = document.getElementById("senhaUsuario");
const confirmarSenha = document.getElementById("confirmarSenha");

const barra = document.getElementById("barraForca");
const texto = document.getElementById("textoForca");
const textoConfirmacao = document.getElementById("textoConfirmacao");

// Regras
const regraTamanho = document.getElementById("regraTamanho");
const regraMaiuscula = document.getElementById("regraMaiuscula");
const regraMinuscula = document.getElementById("regraMinuscula");
const regraNumero = document.getElementById("regraNumero");
const regraEspecial = document.getElementById("regraEspecial");

// Formulário
const form = document.querySelector("form");

// ==============================
// MOSTRAR / OCULTAR SENHA
// ==============================

document.getElementById("toggleSenha").addEventListener("click", () => {

    senhaInput.type =
        senhaInput.type === "password"
            ? "text"
            : "password";
});

document.getElementById("toggleConfirmarSenha").addEventListener("click", () => {

    confirmarSenha.type =
        confirmarSenha.type === "password"
            ? "text"
            : "password";
});

// ==============================
// EVENTOS
// ==============================

senhaInput.addEventListener("input", validarSenha);

confirmarSenha.addEventListener("input", validarConfirmacao);

// ==============================
// VALIDAR SENHA
// ==============================

function validarSenha() {

    const senha = senhaInput.value;

    let pontos = 0;

    // Critérios
    const tamanho = senha.length >= 8;
    const maiuscula = /[A-Z]/.test(senha);
    const minuscula = /[a-z]/.test(senha);
    const numero = /[0-9]/.test(senha);
    const especial = /[^A-Za-z0-9]/.test(senha);

    // Atualiza lista visual
    atualizarRegra(
        regraTamanho,
        tamanho,
        "Mínimo 8 caracteres"
    );

    atualizarRegra(
        regraMaiuscula,
        maiuscula,
        "Letra maiúscula"
    );

    atualizarRegra(
        regraMinuscula,
        minuscula,
        "Letra minúscula"
    );

    atualizarRegra(
        regraNumero,
        numero,
        "Número"
    );

    atualizarRegra(
        regraEspecial,
        especial,
        "Caractere especial"
    );

    // Soma pontos
    if (tamanho) pontos++;
    if (maiuscula) pontos++;
    if (minuscula) pontos++;
    if (numero) pontos++;
    if (especial) pontos++;

    // Reset barra
    barra.className = "progress-bar";

    // Campo vazio
    if (senha.length === 0) {

        barra.style.width = "0%";

        texto.textContent =
            "Digite uma senha";

        validarConfirmacao();

        return;
    }

    // Senha fraca
    if (pontos <= 2) {

        barra.style.width = "33%";

        barra.classList.add("bg-danger");

        texto.textContent =
            "Senha fraca";
    }

    // Senha média
    else if (pontos <= 4) {

        barra.style.width = "66%";

        barra.classList.add("bg-warning");

        texto.textContent =
            "Senha média";
    }

    // Senha forte
    else {

        barra.style.width = "100%";

        barra.classList.add("bg-success");

        texto.textContent =
            "Senha forte";
    }

    validarConfirmacao();
}

// ==============================
// ATUALIZAR REGRAS
// ==============================

function atualizarRegra(elemento, valido, textoRegra) {

    if (valido) {

        elemento.classList.remove("regra-fail");

        elemento.classList.add("regra-ok");

        elemento.innerHTML =
            `✅ ${textoRegra}`;
    }

    else {

        elemento.classList.remove("regra-ok");

        elemento.classList.add("regra-fail");

        elemento.innerHTML =
            `❌ ${textoRegra}`;
    }
}

// ==============================
// VALIDAR CONFIRMAÇÃO
// ==============================

function validarConfirmacao() {

    const senha = senhaInput.value;

    const confirmacao = confirmarSenha.value;

    // Campo vazio
    if (confirmacao.length === 0) {

        textoConfirmacao.textContent = "";

        return false;
    }

    // Senhas iguais
    if (senha === confirmacao) {

        textoConfirmacao.textContent =
            "✅ Senhas coincidem";

        textoConfirmacao.className =
            "text-success";

        return true;
    }

    // Senhas diferentes
    else {

        textoConfirmacao.textContent =
            "❌ As senhas não coincidem";

        textoConfirmacao.className =
            "text-danger";

        return false;
    }
}

// ==============================
// IMPEDIR ENVIO INVÁLIDO
// ==============================

form.addEventListener("submit", (e) => {

    const senha = senhaInput.value;

    const senhaValida =
        senha.length >= 8 &&
        /[A-Z]/.test(senha) &&
        /[a-z]/.test(senha) &&
        /[0-9]/.test(senha) &&
        /[^A-Za-z0-9]/.test(senha);

    const confirmacaoValida =
        validarConfirmacao();

    // Senha inválida
    if (!senhaValida) {

        e.preventDefault();

        texto.textContent =
            "❌ A senha não atende os requisitos.";

        texto.className =
            "text-danger";

        return;
    }

    // Confirmação inválida
    if (!confirmacaoValida) {

        e.preventDefault();

        textoConfirmacao.textContent =
            "❌ As senhas não coincidem";

        textoConfirmacao.className =
            "text-danger";

        return;
    }
});