<template>
  <div class="container-fluid mt-4">
    <h1 class="h1">Histórico do Estoque</h1>
    <b-alert :show="loading" variant="info">Carregando...</b-alert>
    <b-row>
      <b-col>
        <table class="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Estoque</th>
              <th>Quantidade</th>
              <th>Data do registro</th>
              <th>&nbsp;</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="stockHistory in stockHistories" :key="stockHistory.id">
              <td>{{ stockHistory.id }}</td>
              <td>{{ stockHistory.stock.actionFigure.name }}</td>
              <td>{{ stockHistory.amount }}</td>
              <td>{{ stockHistory.date }}</td>
            </tr>
          </tbody>
        </table>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import stockHistoryService from '@/api/stock-history/service'
import moment from 'moment'
export default {
  data () {
    return {
      loading: false,
      stockHistories: []
    }
  },
  async beforeMount () {
    await this.refresh()
  },
  methods: {
    async refresh () {
      this.loading = true
      const stockHistories = await stockHistoryService.findAll()
      stockHistories
        .map(stockHistory => {
          const date = moment(stockHistory.date, 'YYYY-MM-DD hh:mm:ss').format('DD/MM/YYYY hh:mm:ss')
          const lastTime = moment(stockHistory.date, 'YYYY-MM-DD hh:mm:ss').fromNow()
          stockHistory.date = `${date} (${lastTime})`
          return stockHistory
        })
      this.stockHistories = stockHistories
      this.loading = false
    }
  }
}
</script>
