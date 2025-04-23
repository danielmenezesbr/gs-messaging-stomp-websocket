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
helm upgrade --install my-app-devops ./charts/my-app -n devops -f values/devops.yaml
helm upgrade --install my-app-demo ./charts/my-app -n demo -f values/demo.yaml
helm upgrade --install my-app-prod ./charts/my-app -n prod -f values/prod.yaml
```

# teste (visualizar os logs)

```bash
kubectl logs -f -l app=my-app -n devops
```
