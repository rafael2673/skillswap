import React from "react";
import "./App.css";
import { ConfigProvider, Layout, Button, Row, Col } from "antd";
import logo from "./images/SkillswapLogo.png";

const { Header, Content } = Layout;

const App: React.FC = () => (
  <ConfigProvider
    theme={{
      token: {
        // Seed Token
        colorPrimary: "#6F42C1",
        borderRadius: 6,

        // Alias Token
        colorBgContainer: "#FFFFFF",
      },
    }}
  >
    <Header className="header">
      <div className="demo-logo">
        <img src={logo} alt="Logo Skillswap" width={64} height={64} />
      </div>
      <Button type="primary">Junte-se ao Skillswap</Button>
    </Header>
    <Content>
      <Row>
        <Col className="session1" flex="auto">
          <div>
            <h1>Skillswap: </h1>
            <h1>Compartilhe suas habilidades, aprenda algo novo.</h1>
            <p>
              Skillswap é uma plataforma inovadora que conecta pessoas dentro da
              sua comunidade para que elas compartilhem seus conhecimentos e
              aprendam juntas, sem a necessidade de dinheiro. Imagine um mundo
              onde cada um pode ensinar o que sabe e aprender algo novo em
              troca, apenas por amor ao aprendizado!
            </p>
            <Button type="primary">Junte-se ao Skillswap</Button>
          </div>
        </Col>
      </Row>
      <Row>
        <Col flex="auto" className="session2">
          <div>
            <h2>Despertando o potencial da comunidade</h2>
          </div>
        </Col>
      </Row>
    </Content>
  </ConfigProvider>
);

export default App;
