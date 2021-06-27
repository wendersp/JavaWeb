package entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author wender
 */
@Entity
@Table(name = "fornecedor", schema = "javaweb")
@NamedQueries({
    @NamedQuery(
            name = "Forncedor.findByNome",
            query = "SELECT f FROM Fornecedor f WHERE f.nome LIKE :nome"
    ),
    @NamedQuery(
            name = "Forncedor.findByCnpj",
            query = "SELECT f FROM Fornecedor f WHERE f.cnpj = :cnpj"
    )
})
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", length = 150, nullable = false)
    private String nome;
    @Column(name = "cnpj", length = 18, nullable = false, unique = true)
    private String cnpj;
    @Column(name = "insc_estadual", length = 20)
    private String inscEstadual;
    @Column(name = "insc_municipal", length = 20)
    private String inscMunicipal;
    @Column(name = "telefone", length = 15)
    private String telefone;
    @Column(name = "email", length = 50)
    private String email;    
    @Column(name = "cidade", length = 100)
    private String cidade;
    @Column(name = "bairro", length = 100)
    private String bairro;
    @Column(name = "endereco", length = 150)
    private String endereco;
    @Column(name = "cep", length = 10)
    private String cep;
    @Column(name = "observacao", length = 255)
    private String observacao;
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscEstadual() {
        return inscEstadual;
    }

    public void setInscEstadual(String inscEstadual) {
        this.inscEstadual = inscEstadual;
    }

    public String getInscMunicipal() {
        return inscMunicipal;
    }

    public void setInscMunicipal(String inscMunicipal) {
        this.inscMunicipal = inscMunicipal;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    
        
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sige.modelo.entidade.Fornecedor[ id=" + id + " ]";
    }
    
}
