public class PessoaJuridica extends Cliente{
    private String cnpj;

    public PessoaJuridica(String nome, String cnpj){
        super(nome);
        this.cnpj = cnpj;
    }

    public String getCpfCnpj(){
        return cnpj;
    }

    @Override
    public String toString() {
        return "Nome: "+this.getNome()+", Cnpj: "+this.cnpj;
    }
}
