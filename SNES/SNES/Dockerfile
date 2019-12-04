FROM microsoft/dotnet:2.1-aspnetcore-runtime AS base
WORKDIR /app
EXPOSE 80
EXPOSE 443

FROM microsoft/dotnet:2.1-sdk AS build
WORKDIR /src
COPY ["WABA/WABA.csproj", "WABA/"]
RUN dotnet restore "WABA/WABA.csproj"
COPY . .
WORKDIR "/src/WABA"
RUN dotnet build "WABA.csproj" -c Release -o /app

FROM build AS publish
RUN dotnet publish "WABA.csproj" -c Release -o /app

FROM base AS final
WORKDIR /app
COPY --from=publish /app .
ENTRYPOINT ["dotnet", "WABA.dll"]