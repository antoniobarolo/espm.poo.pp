public abstract class Cliente {
    private String nome;

    public Cliente(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public abstract String getCpfCnpj();

    @Override
    public String toString() {
        return "Nome: "+this.nome;
    }
}
