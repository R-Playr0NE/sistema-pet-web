// CPF

const cpf = document.getElementById("cpfUsuario");

if (cpf) {

    cpf.addEventListener("input", function () {

        let valor = cpf.value.replace(/\D/g, "");

        valor = valor.replace(/(\d{3})(\d)/, "$1.$2");
        valor = valor.replace(/(\d{3})(\d)/, "$1.$2");
        valor = valor.replace(/(\d{3})(\d{1,2})$/, "$1-$2");

        cpf.value = valor;
    });
}

// Telefone

const telefone = document.getElementById("telefoneUsuario");

if (telefone) {

    telefone.addEventListener("input", function () {

        let valor = telefone.value.replace(/\D/g, "");

        valor = valor.replace(/^(\d{2})(\d)/g, "($1) $2");
        valor = valor.replace(/(\d{5})(\d)/, "$1-$2");

        telefone.value = valor;
    });
}