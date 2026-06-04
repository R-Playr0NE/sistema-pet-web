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

function configurarToggleSenha(inputId, buttonId) {

    const input = document.getElementById(inputId);
    const button = document.getElementById(buttonId);

    if (!input || !button) {
        return;
    }

    const icon = button.querySelector("i");

    button.addEventListener("click", () => {

        const mostrando = input.type === "password";

        input.type = mostrando ? "text" : "password";

        icon.className = mostrando
            ? "bi bi-eye-fill"
            : "bi bi-eye-slash-fill";
    });
}

// Cadastro
configurarToggleSenha(
    "senhaUsuario",
    "toggleSenha"
);

configurarToggleSenha(
    "confirmarSenha",
    "toggleConfirmarSenha"
);

// ==============================
// CÓDIGO DE VALIDAÇÃO
// SÓ EXECUTA NO CADASTRO
// ==============================

if (senhaInput && confirmarSenha && form) {

    senhaInput.addEventListener("input", validarSenha);

    confirmarSenha.addEventListener(
        "input",
        validarConfirmacao
    );

    // ==============================
    // VALIDAR SENHA
    // ==============================

    function validarSenha() {

        const senha = senhaInput.value;

        let pontos = 0;

        const tamanho = senha.length >= 8;
        const maiuscula = /[A-Z]/.test(senha);
        const minuscula = /[a-z]/.test(senha);
        const numero = /[0-9]/.test(senha);
        const especial = /[^A-Za-z0-9]/.test(senha);

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

        if (tamanho) pontos++;
        if (maiuscula) pontos++;
        if (minuscula) pontos++;
        if (numero) pontos++;
        if (especial) pontos++;

        barra.className = "progress-bar";

        if (senha.length === 0) {

            barra.style.width = "0%";

            texto.textContent =
                "Digite uma senha";

            validarConfirmacao();

            return;
        }

        if (pontos <= 2) {

            barra.style.width = "33%";

            barra.classList.add("bg-danger");

            texto.textContent =
                "Senha fraca";
        }

        else if (pontos <= 4) {

            barra.style.width = "66%";

            barra.classList.add("bg-warning");

            texto.textContent =
                "Senha média";
        }

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

    function atualizarRegra(
        elemento,
        valido,
        textoRegra
    ) {

        if (valido) {

            elemento.classList.remove(
                "regra-fail"
            );

            elemento.classList.add(
                "regra-ok"
            );

            elemento.innerHTML =
                `<i class="bi bi-key-fill"></i> ${textoRegra}`;
        }

        else {

            elemento.classList.remove(
                "regra-ok"
            );

            elemento.classList.add(
                "regra-fail"
            );

            elemento.innerHTML =
                `<i class="bi bi-asterisk"></i> ${textoRegra}`;
        }
    }

    // ==============================
    // VALIDAR CONFIRMAÇÃO
    // ==============================

    function validarConfirmacao() {

        const senha = senhaInput.value;

        const confirmacao =
            confirmarSenha.value;

        if (confirmacao.length === 0) {

            textoConfirmacao.textContent =
                "";

            return false;
        }

        if (senha === confirmacao) {

            textoConfirmacao.innerHTML =
                `<i class="bi bi-key-fill"></i> Senhas coincidem`;

            textoConfirmacao.className =
                "text-success";

            return true;
        }

        else {

            textoConfirmacao.innerHTML =
                `<i class="bi bi-asterisk"></i> As senhas não coincidem`;

            textoConfirmacao.className =
                "text-danger";

            return false;
        }
    }

    // ==============================
    // IMPEDIR ENVIO INVÁLIDO
    // ==============================

    form.addEventListener(
        "submit",
        (e) => {

            const senha =
                senhaInput.value;

            const senhaValida =
                senha.length >= 8 &&
                /[A-Z]/.test(senha) &&
                /[a-z]/.test(senha) &&
                /[0-9]/.test(senha) &&
                /[^A-Za-z0-9]/.test(senha);

            const confirmacaoValida =
                validarConfirmacao();

            if (!senhaValida) {

                e.preventDefault();

                texto.innerHTML =
                    `<i class="bi bi-asterisk"></i> A senha não atende os requisitos.`;

                texto.className =
                    "text-danger";

                return;
            }

            if (!confirmacaoValida) {

                e.preventDefault();

                textoConfirmacao.innerHTML =
                    `<i class="bi bi-asterisk"></i> As senhas não coincidem`;

                textoConfirmacao.className =
                    "text-danger";
            }
        }
    );
}