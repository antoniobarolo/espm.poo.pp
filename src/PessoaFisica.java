public class PessoaFisica extends Cliente {
    private String cpf;

    public PessoaFisica(String nome, String cpf){
        super(nome);
        this.cpf = cpf;
    }

    public String getCpfCnpj(){
        return cpf;
    }

    @Override
    public String toString() {
        return "Nome: "+this.getNome()+", Cpf: "+this.cpf;
    }
}
