<template>
  <div class="container-fluid mt-4">
    <h1 class="h1">Fabricantes</h1>
    <b-alert :show="loading" variant="info">Carregando...</b-alert>
    <b-row>
      <b-col>
        <table class="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nome</th>
              <th>&nbsp;</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="manufacturer in manufacturers" :key="manufacturer.id">
              <td>{{ manufacturer.id }}</td>
              <td>{{ manufacturer.name }}</td>
              <td class="text-right">
                <a href="#" @click.prevent="fill(manufacturer)">Editar</a> -
                <a href="#" @click.prevent="remove(manufacturer.id)">Remover</a>
              </td>
            </tr>
          </tbody>
        </table>
      </b-col>
      <b-col lg="3">
        <b-card :title="(model.id ? 'Editar Fabricante ID#' + model.id : 'Novo Fabricante')">
          <form @submit.prevent="create">
            <b-form-group label="Nome">
              <b-form-input type="text" v-model="model.name"></b-form-input>
            </b-form-group>
            <div>
              <b-btn type="submit" variant="success">Salvar</b-btn>
            </div>
          </form>
        </b-card>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import service from '@/api/manufacturer/service'
export default {
  data () {
    return {
      loading: false,
      manufacturers: [],
      model: {}
    }
  },
  async created () {
    this.refresh()
  },
  methods: {
    async refresh () {
      this.loading = true
      this.manufacturers = await service.findAll()
      this.loading = false
    },
    async fill (manufacturer) {
      this.model = Object.assign({}, manufacturer)
    },
    async create () {
      if (this.model.id) {
        await service.update(this.model.id, this.model)
      } else {
        await service.save(this.model)
      }
      this.model = {}
      await this.refresh()
    },
    async remove (id) {
      if (confirm('Tem certeza que deseja excluir esse Fabricante?')) {
        if (this.model.id === id) {
          this.model = {}
        }
        await service.delete(id)
        await this.refresh()
      }
    }
  }
}
</script>
