# preparar o minikube (somente uma vez)

```bash
minikube start
```

# criar as namespaces (somente uma vez)

```bash
kubectl create namespace devops
kubectl create namespace demo
kubectl create namespace prod
```

# instalar a app spring boot em cada namespace (sempre que o helm chart é alterado)

```bash
cd helm
helm upgrade --install websocketpoc-app-devops ./charts/websocketpoc-app -n devops -f values/devops.yaml
helm upgrade --install websocketpoc-app-demo ./charts/websocketpoc-app -n demo -f values/demo.yaml
helm upgrade --install websocketpoc-app-prod ./charts/websocketpoc-app -n prod -f values/prod.yaml
```

# teste (visualizar os logs)

```bash
kubectl logs -f -l app=websocketpoc-app -n devops
```

kubectl port-forward svc/websocketpoc-app-devops 8080:8080 -n devops


# prometheus

```
cd helm/prometheus
````

```
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo update
```

Instalação básica:

```
helm install prometheus prometheus-community/prometheus \
  --version 25.26.0 \
  --namespace prometheus --create-namespace
```


Instalação com values.yaml:
```
helm install prometheus prometheus-community/prometheus \
  --version 25.26.0 \
  --namespace prometheus --create-namespace \
  -f values.yaml
```