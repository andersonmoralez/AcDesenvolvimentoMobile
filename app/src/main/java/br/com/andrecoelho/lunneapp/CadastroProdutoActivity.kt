package br.com.andrecoelho.lunneapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_cadastro_produto.*
import java.lang.Float.parseFloat
import java.lang.Integer.parseInt
import java.lang.Long.parseLong

class CadastroProdutoActivity : AppCompatActivity() {

    var par1 = arrayOf("UN", "PT","CX","MT","KG")
    var selecao1 = 0

    var origem = arrayOf(0,1,2)
    var selecao2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_produto)

        var produto = Produtos()

        //spinners
        val spinner1 = this.findViewById<Spinner>(R.id.spinnerUndDesc)
        val spinner2 = this.findViewById<Spinner>(R.id.spinnerOrigem)

        //Adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, this.par1)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, this.origem)


        spinner1.adapter = adapter
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selecao1 = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        spinner2.adapter = adapter2
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selecao2 = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        buttonSalvarProduto.setOnClickListener {

            produto.codProduto = parseLong(insertCodProduto1.text.toString())
            produto.codEan = parseLong(insertCodEan.text.toString())
            produto.ref = insertReferencia.text.toString()
            produto.ncm = insertNcm.text.toString()
            produto.cor = insertCorProduto.text.toString()
            produto.descricaoReduzida = insertDescReduzida.text.toString()
            produto.descricao = insertDesc.text.toString()
            produto.gradeVenda = spinnerUndDesc.selectedItem.toString()
            produto.descricaoUnidadeVend = insertDescUnid.text.toString()
            produto.unidadeVenda = insertUnidade.text.toString()
            produto.origem = parseInt(spinnerOrigem.selectedItem.toString())
            produto.custo = parseFloat(insertCusto.text.toString())
            produto.preco = parseFloat(insertPreco.text.toString())
            produto.altura = parseFloat(insertAltura.text.toString())
            produto.largura = parseFloat(insertLargura.text.toString())
            produto.comprimento = parseFloat(insertComprimento.text.toString())
            produto.peso = parseFloat(insertPeso.text.toString())

            taskAtualizar(produto)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Incluir Produto"
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if(id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskAtualizar(produtos: Produtos) {
        // Thread para salvar a Cor
        Thread {
            ProdutosService.save(produtos)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}
