<template>
  <div class="container-fluid mt-4">
    <h1 class="h1">Estoque</h1>
    <b-alert :show="loading" variant="info">Carregando...</b-alert>
    <b-row>
      <b-col>
        <table class="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Produto</th>
              <th>Quantidade</th>
              <th>&nbsp;</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="stock in stocks" :key="stock.id">
              <td>{{ stock.id }}</td>
              <td>{{ stock.actionFigure.name }}</td>
              <td>{{ stock.amount }}</td>
              <td class="text-right">
                <a href="#" @click.prevent="fill(stock)">Realizar Entrada/Saida</a>
              </td>
            </tr>
          </tbody>
        </table>
      </b-col>
      <b-col lg="3">
        <b-card :title="(model.id ? 'Entrada/Saída no Estoque ID#' + model.id : 'Estoque')">
          <form @submit.prevent="create">
            <b-form-group label="Produto">
              <b-form-select
                v-model="select.actionFigure.selected"
                :options="select.actionFigure.options"
                @change="changeActionFigure($event)"
              ></b-form-select>
            </b-form-group>
            <b-form-group label="Quantidade">
              <b-form-input
                type="number"
                v-model="model.amount"
              ></b-form-input>
            </b-form-group>
            <div>
              <b-btn
                type="submit"
                variant="success"
              >Salvar</b-btn>
            </div>
          </form>
        </b-card>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import stockService from '@/api/stock/service'
import actionFigureService from '@/api/action-figure/service'
export default {
  data () {
    return {
      loading: false,
      stocks: [],
      select: {
        actionFigure: {
          selected: null,
          options: []
        }
      },
      model: {}
    }
  },
  async created () {
    this.refresh()
  },
  async beforeMount () {
    await this._refreshActionFigures()
  },
  methods: {
    async refresh () {
      this.loading = true
      this.stocks = await stockService.findAll()
      this.loading = false
    },
    async fill (stock) {
      this._fromModelToSelected(stock)
      this.model = Object.assign({}, stock)
    },
    async clear () {
      this.model = {}
      this.select.actionFigure.selected = null
    },
    async create () {
      if (this.model.id) {
        await stockService.update(this.model.id, this.model)
      } else {
        await stockService.save(this.model)
      }
      await this.clear()
      await this.refresh()
    },
    changeActionFigure (id) {
      this.model = {
        ...this.model,
        actionFigure: {
          id
        }
      }
    },
    async _refreshActionFigures () {
      const actionFigures = await actionFigureService.findAll()
      const options = actionFigures.map(actionFigure => {
        const { id, name } = actionFigure
        const option = {
          key: id,
          value: id,
          text: name
        }
        return option
      })
      this.select.actionFigure.options = options
    },
    _fromModelToSelected (field) {
      const selected = field.id
      this.select.actionFigure.selected = selected
    }
  }
}
</script>
