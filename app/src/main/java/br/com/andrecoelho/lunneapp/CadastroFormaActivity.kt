package br.com.andrecoelho.lunneapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.text.set
import kotlinx.android.synthetic.main.activity_cadastro_forma.*
import java.lang.Integer.parseInt
import java.lang.Long.parseLong

class CadastroFormaActivity : DebugActivity() {

    var par1 = arrayOf(0, 15, 30, 45, 60, 75, 90, 105, 120)
    var selecao1 = 0
    var selecao2 = 0
    var selecao3 = 0
    var selecao4 = 0
    var selecao5 = 0
    var selecao6 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_cadastro_forma)

        //Object
        val forma = FormaDePagamento()

        //Spinners
        val spinnerP1 = this.findViewById<Spinner>(R.id.spinnerParcela1)
        val spinnerP2 = this.findViewById<Spinner>(R.id.spinnerParcela2)
        val spinnerP3 = this.findViewById<Spinner>(R.id.spinnerParcela3)
        val spinnerP4 = this.findViewById<Spinner>(R.id.spinnerParcela4)
        val spinnerP5 = this.findViewById<Spinner>(R.id.spinnerParcela5)
        val spinnerP6 = this.findViewById<Spinner>(R.id.spinnerParcela6)
        //Adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, this.par1)

        spinnerP1.adapter = adapter
        spinnerP1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selecao1 = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinnerP2.adapter = adapter
        spinnerP2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selecao2 = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinnerP3.adapter = adapter
        spinnerP3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selecao3 = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinnerP4.adapter = adapter
        spinnerP4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selecao4 = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinnerP5.adapter = adapter
        spinnerP5.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selecao5 = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinnerP6.adapter = adapter
        spinnerP6.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selecao6 = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


        //atribuicao
        buttonSalvarForma.setOnClickListener {
            forma.codigoFormaDePgto = parseLong(insertCodForma.text.toString())
            forma.descricaoFormaDePgto = insertDescricao.text.toString()
            forma.parcela1 = parseInt(spinnerP1.selectedItem.toString())
            forma.parcela2 = parseInt(spinnerP2.selectedItem.toString())
            forma.parcela3 = parseInt(spinnerP3.selectedItem.toString())
            forma.parcela4 = parseInt(spinnerP4.selectedItem.toString())
            forma.parcela5 = parseInt(spinnerP5.selectedItem.toString())
            forma.parcela6 = parseInt(spinnerP6.selectedItem.toString())

            taskAtualizar(forma)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Incluir Foma de Pagamento"

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if(id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskAtualizar(forma: FormaDePagamento) {
        // Thread para salvar a Cor
        Thread {
            FormaDePgtoService.save(forma)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}
