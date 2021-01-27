<template>
  <div class="container-fluid mt-4">
    <h1 class="h1">Action Figures</h1>
    <b-alert :show="loading" variant="info">Carregando...</b-alert>
    <b-row>
      <b-col>
        <table class="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nome</th>
              <th>Preço</th>
              <th>Fabricante</th>
              <th>&nbsp;</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="actionFigure in actionFigures" :key="actionFigure.id">
              <td>{{ actionFigure.id }}</td>
              <td>{{ actionFigure.name }}</td>
              <td>{{ actionFigure.price }}</td>
              <td>{{ actionFigure.manufacturer.name }}</td>
              <td class="text-right">
                <a href="#" @click.prevent="fill(actionFigure)">Editar</a> -
                <a href="#" @click.prevent="remove(actionFigure.id)">Remover</a>
              </td>
            </tr>
          </tbody>
        </table>
      </b-col>
      <b-col lg="5">
        <b-card :title="(model.id ? 'Editar Action Figure ID#' + model.id : 'Nova Action Figure')">
          <form @submit.prevent="create">
            <b-form-group label="Nome">
              <b-form-input type="text" v-model="model.name"></b-form-input>
            </b-form-group>
            <b-form-group label="Descrição breve">
              <b-form-textarea
                v-model="model.shortDescription"
                rows="3"
                no-resize
                placeholder="Descreva o produto brevemente..."
              ></b-form-textarea>
            </b-form-group>
            <b-form-group label="Descrição detalhada (opcional)">
              <b-form-textarea
                v-model="model.longDescription"
                rows="10"
                no-resize
                placeholder="Descreva o produto detalhadamente..."
              ></b-form-textarea>
            <b-form-group label="Preço">
              <b-form-input type="number" v-model="model.price"></b-form-input>
            </b-form-group>
            </b-form-group>
            <b-form-group label="Altura aproximada (cm)">
              <b-form-input type="number" v-model="model.approximateHeight"></b-form-input>
            </b-form-group>
            <b-form-group label="Idade recomendada">
              <b-form-select
                v-model="select.recommendedAge.selected"
                :options="select.recommendedAge.options"
                @change="changeRecommendedAge($event)"
              ></b-form-select>
            </b-form-group>
            <b-form-group label="Fabricante">
              <b-form-select
                v-model="select.manufacturer.selected"
                :options="select.manufacturer.options"
                @change="changeManufacturer($event)"
              ></b-form-select>
            </b-form-group>
            <b-form-group label="Materiais">
              <b-form-checkbox
                v-for="material in checkbox.material.loaded"
                :key="material.id"
                v-model="checkbox.material.selected"
                :id="material.name"
                :value="material.id"
              >{{ material.name }} </b-form-checkbox>
            </b-form-group>
            <b-form-group label="Categorias relacionadas">
              <b-form-checkbox
                v-for="category in checkbox.category.loaded"
                :key="category.id"
                v-model="checkbox.category.selected"
                :id="category.name"
                :value="category.id"
              >{{ category.name }} </b-form-checkbox>
            </b-form-group>
            <div>
              <b-btn type="submit" variant="success">Salvar</b-btn>
              <b-btn
                type="button"
                variant="secondary"
                :disabled="(model.id ? false : true)"
                @click.prevent="clear()">Cancelar</b-btn>
            </div>
          </form>
        </b-card>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import actionFigureService from '@/api/action-figure/service'
import manufacturerService from '@/api/manufacturer/service'
import materialService from '@/api/material/service'
import categoryService from '@/api/category/service'
export default {
  data () {
    return {
      loading: false,
      actionFigures: [],
      checkbox: {
        category: {
          selected: [],
          loaded: []
        },
        material: {
          selected: [],
          loaded: []
        }
      },
      select: {
        manufacturer: {
          selected: null,
          options: []
        },
        recommendedAge: {
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
    this._refreshRecommendedAges()
    this._refreshManufacturers()
    this._refreshMaterials()
    this._refreshCategories()
  },
  methods: {
    async refresh () {
      this.loading = true
      this.actionFigures = await actionFigureService.findAll()
      this.loading = false
    },
    async fill (actionFigure) {
      this.model = Object.assign({}, actionFigure)
      const { recommendedAge, manufacturer, materials, categories } = actionFigure
      this._fromModelToSelected(recommendedAge, 'recommendedAge')
      this._fromModelToSelected(manufacturer, 'manufacturer')
      this._fromModelToChecked(materials, 'material')
      this._fromModelToChecked(categories, 'category')
    },
    async clear () {
      this.model = {}
      this.checkbox.material.selected = []
      this.checkbox.category.selected = []
      this.select.recommendedAge.selected = null
      this.select.manufacturer.selected = null
    },
    async create () {
      if (this.model.id) {
        await actionFigureService.update(this.model.id, this.model)
      } else {
        const materials = await this._convertMaterialsToModel()
        const categories = await this._convertCategoriesToModel()
        this.model = {
          ...this.model,
          materials,
          categories
        }
        await actionFigureService.save(this.model)
      }
      await this.clear()
      await this.refresh()
    },
    async remove (id) {
      if (confirm('Tem certeza que deseja excluir esse Action Figure?')) {
        if (this.model.id === id) {
          this.model = {}
        }
        await actionFigureService.delete(id)
        await this.refresh()
      }
    },
    _refreshRecommendedAges () {
      const recommendedAges = [5, 8, 12, 15, 18]
      const options = recommendedAges.map(recommendedAge => {
        const option = {
          key: recommendedAge,
          value: recommendedAge,
          text: `+ ${recommendedAge} anos`
        }
        return option
      })
      this.select.recommendedAge.options = options
    },
    async _refreshManufacturers () {
      const manufacturers = await manufacturerService.findAll()
      const options = manufacturers.map(manufacturer => {
        const { id, name } = manufacturer
        const option = {
          key: id,
          value: id,
          text: name
        }
        return option
      })
      this.select.manufacturer.options = options
    },
    async _refreshMaterials () {
      const materials = await materialService.findAll()
      this.checkbox.material.loaded = materials
    },
    async _refreshCategories () {
      const categories = await categoryService.findAll()
      this.checkbox.category.loaded = categories
    },
    changeRecommendedAge (recommendedAge) {
      this.model = {
        ...this.model,
        recommendedAge
      }
    },
    changeManufacturer (id) {
      this.model = {
        ...this.model,
        manufacturer: {
          id
        }
      }
    },
    _convertMaterialsToModel () {
      return this.checkbox.material.selected
        .map(materialId => {
          const material = {
            id: materialId
          }
          return material
        })
    },
    _convertCategoriesToModel () {
      return this.checkbox.category.selected
        .map(categoryId => {
          const category = {
            id: categoryId
          }
          return category
        })
    },
    _fromModelToSelected (field, propertyName = 'recommendedAge' | 'manufacturer') {
      const selected = field.id ? field.id : field
      this.select[propertyName].selected = selected
    },
    _fromModelToChecked (list, propertyName = 'material' | 'category') {
      const selected = list
        .map(element => element.id)
      this.checkbox[propertyName].selected = selected
    }
  }
}
</script>
